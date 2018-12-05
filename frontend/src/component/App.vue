<template>
  <router-view></router-view>
</template>

<script>
  export default {
    mounted() {
        //Initialize the user email to the one stored in the local storage
        if (localStorage.getItem("userEmail")) {
            this.$store.commit('setUserEmail', localStorage.getItem("userEmail"));
        }
        this.$router.push('/question');
    },
  };
</script>
