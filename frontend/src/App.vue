<template>
  <div class="container">
    <h2>User Registration</h2>
    <UserForm />
  </div>
</template>

<script>
import UserForm from './components/UserForm.vue'

export default {
  components: {
    UserForm
  }
}
</script>

<style>
.container {
  max-width: 600px;
  margin: 40px auto;
  font-family: Arial, sans-serif;
}
</style>
