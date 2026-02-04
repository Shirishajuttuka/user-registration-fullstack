<template>
  <div>

    <!-- FORM -->
    <form @submit.prevent="saveUser">

      <input v-model="user.fullName" placeholder="Full Name" required />

      <input v-model="user.email" placeholder="Email" required />

      <input v-model="user.mobile" placeholder="Mobile" required />

      <input v-model="user.password" type="password" placeholder="Password" required />

      <input v-model="user.dob" type="date" />

      <button type="submit">
        {{ user.id ? "Update" : "Register" }}
      </button>

    </form>

    <hr />

    <!-- USERS TABLE -->
    <table border="1" width="100%">
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>DOB</th>
        <th>Actions</th>
      </tr>

      <tr v-for="u in users" :key="u.id">
        <td>{{ u.fullName }}</td>
        <td>{{ u.email }}</td>
        <td>{{ u.mobile }}</td>
        <td>{{ u.dob }}</td>
        <td>
          <button @click="editUser(u)">Edit</button>
          <button @click="deleteUser(u.id)">Delete</button>
        </td>
      </tr>
    </table>

  </div>
</template>

<script>
import axios from "axios"

export default {

  data() {
    return {
      users: [],
      user: {
        id: "",
        fullName: "",
        email: "",
        mobile: "",
        password: "",
        dob: ""
      }
    }
  },

  mounted() {
    this.loadUsers()
  },

  methods: {

    async loadUsers() {
      const res = await axios.get("http://localhost:8080/api/users")
      this.users = res.data
    },

    async saveUser() {

      if (this.user.id) {
        await axios.put(`http://localhost:8080/api/users/${this.user.id}`, this.user)
      } else {
        await axios.post("http://localhost:8080/api/users", this.user)
      }

      this.reset()
      this.loadUsers()
    },

    editUser(u) {
      this.user = { ...u }
    },

    async deleteUser(id) {
      await axios.delete(`http://localhost:8080/api/users/${id}`)
      this.loadUsers()
    },

    reset() {
      this.user = {
        id: "",
        fullName: "",
        email: "",
        mobile: "",
        password: "",
        dob: ""
      }
    }

  }
}
</script>

<style>
input {
  display: block;
  margin: 10px 0;
  padding: 8px;
  width: 100%;
}

button {
  margin-right: 5px;
  padding: 6px 10px;
}
</style>
