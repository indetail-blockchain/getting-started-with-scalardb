<template>
    <b-container class="d-flex justify-content-center align-items-center flex-column" style="height:100%">
        <div>
            <b-alert :show="isShowAlert"
                     variant="warning">
                {{error}}
            </b-alert>
        </div>
        <div>
            <b-card header="Scalar Q&A" bg-variant="secondary" text-variant="white">
                <b-form @submit="login">
                    <b-form-group label="Email" v-if="!loading">
                        <b-form-input id="email"
                                      type="email"
                                      v-model="email"
                                      required>
                        </b-form-input>
                    </b-form-group>
                    <b-form-group label="Password" v-if="!loading">
                        <b-form-input id="password"
                                      type="password"
                                      v-model="password"
                                      required>
                        </b-form-input>
                    </b-form-group>
                    <div class="d-flex justify-content-center">
                        <b-button type="submit" variant="primary">Login</b-button>
                    </div>
                </b-form>
            </b-card>
        </div>
    </b-container>
</template>
<script>
    import api from '../lib/api';

    export default {
        data: () => ({
            email: '',
            password: '',
            loading: false,
            error: '',
            alertErrorDismissSecs: 3,
            alertErrorDismissCountdown: 0
        }),
        computed: {
            isShowAlert: function () {
                return this.error !== '';
            }
        },
        methods: {
            login() {
                api.post("/login", {
                    email: this.email,
                    password: this.password,
                }).then(response => {
                    //Store token and userEmail in the localstorage for later access
                    localStorage.setItem('jwt', response.headers.authorization);
                    localStorage.setItem('userEmail', this.email);
                    this.$store.commit('setUserEmail', this.email);
                    this.$router.push('/question');
                }).catch(e => {
                    if (e.response && e.response.status == 403) {
                        this.error = "Invalid email or password";
                    } else {
                        console.log(e);
                        this.$toasted.error("An error occurred during the login")
                    }
                });
            },
        },
    };
</script>
