import { exec, spawn } from "child_process"

let gradleProcess

exec('rm hs_err_*.log')

function startGradleProcess() {
  gradleProcess = spawn("./gradlew", ["bootRun"], { stdio: "inherit" })

  gradleProcess.on("error", (err) => {
    console.error(`Failed to start Gradle process: ${err}`)
  })

  gradleProcess.on("close", (code) => {
    if (code !== 0) {
      console.error(`Gradle process exited with code ${code}`)
    }
    console.log("Gradle process has finished.")
  })
}

function stopGradleProcess() {
  if (gradleProcess) {
    console.log("Stopping current Gradle process...")
    gradleProcess.kill()
  }
}

function restartServer() {
  stopGradleProcess()
  startGradleProcess()
}

startGradleProcess();

process.on("message", (message) => {
  if (message === "r") {
    console.log("Changes detected, restarting Gradle process...")
    restartServer()
  }
})

process.on('SIGINT', () => {
  console.log('Received SIGINT (Ctrl-C), shutting down...')
  stopGradleProcess()
  process.exit()
})
