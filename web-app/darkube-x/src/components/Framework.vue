<script setup>
import { ref } from "vue"

defineProps({
  msg: String,
})

const count = ref(0)
</script>

<template>
  <div class="root">
    <div class="body">
      <div class="app">
        <div class="center">
          <a href="https://vite.dev" target="_blank">
            <img src="/src/assets/vite.svg" class="logo" alt="Vite logo" />
          </a>
          <a href="https://vuejs.org/" target="_blank">
            <img src="/src/assets/vue.svg" class="logo vue" alt="Vue logo" />
          </a>
        </div>

        <h1>{{ msg }}</h1>

        <div class="card">
          <button type="button" @click="count++">count is {{ count }}</button>
        </div>

        <p>
          Check out
          <a
            href="https://vuejs.org/guide/quick-start.html#local"
            target="_blank"
            >create-vue</a
          >, the official Vue + Vite starter
        </p>
        <p>
          Learn more about IDE Support for Vue in the
          <a
            href="https://vuejs.org/guide/scaling-up/tooling.html#ide-support"
            target="_blank"
            >Vue Docs Scaling up Guide</a
          >.
        </p>
        <p class="read-the-docs">
          Click on the Vite and Vue logos to learn more
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}

.logo {
  height: 9em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}

.root {
  font-family: Inter, system-ui, Avenir, Helvetica, Arial, sans-serif;
  line-height: 1.5;
  font-weight: 400;

  color-scheme: light dark;
  color: rgba(255, 255, 255, 0.87);
  background-color: #242424;

  font-synthesis: none;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

a {
  font-weight: 500;
  color: #646cff;
  text-decoration: inherit;
}
a:hover {
  color: #535bf2;
}

.body {
  margin: 0;
  display: flex;
  place-items: center;
  min-width: 320px;
  min-height: 100vh;
}

h1 {
  font-size: 3.2em;
  line-height: 1.1;
  font-weight: 600;
}

button {
  border-radius: 8px;
  border: 1px solid transparent;
  padding: 0.6em 1.2em;
  font-size: 1em;
  font-weight: 500;
  font-family: inherit;
  background-color: #1a1a1a;
  cursor: pointer;
  transition: border-color 0.25s;
}
button:hover {
  border-color: #646cff;
}
button:focus,
button:focus-visible {
  outline: 4px auto -webkit-focus-ring-color;
}

.card {
  padding: 2em;
}

.app {
  max-width: 1280px;
  margin: 0 auto;
  padding: 2rem;
  text-align: center;
}

.center {
  display: flex;
  justify-content: center;
  align-items: center;
}

p {
  padding-bottom: 1em;
}

@media (prefers-color-scheme: light) {
  .root {
    color: #213547;
    background-color: #ffffff;
  }
  a:hover {
    color: #747bff;
  }
  button {
    background-color: #f9f9f9;
  }
}
</style>
