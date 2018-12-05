<template>
    <v-app>
        <b-navbar toggleable="md" sticky="true" type="dark" variant="info" class="nav-bar-shadow">

            <b-navbar-brand @click="goQuestion">Scalar Q&A</b-navbar-brand>
            <div class="d-flex flex-column ml-auto">
                <div class="align-self-end">{{userEmail}}</div>
                <b-button class="mt-1 mb-0 btn-sm" type="submit" @click="logout">Logout</b-button>
            </div>
        </b-navbar>
        <v-content>
            <div class="m-4">
                <router-view></router-view>
            </div>
        </v-content>
    </v-app>

</template>

<script>
    export default {
        data: () => ({}),
        computed: {
            userEmail() {
                return this.$store.state.userEmail
            }
        },
        methods: {
            logout() {
                localStorage.removeItem('jwt');
                this.$router.push('/login');
            },
            goQuestion() {
                this.$router.push('/question');
            }
        }
    }
</script>

<style scoped>

</style>