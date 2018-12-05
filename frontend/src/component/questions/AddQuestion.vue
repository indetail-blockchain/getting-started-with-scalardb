<template>
    <b-container>
        <b-row class="mt-3">
            <b-col>
                <b-form @submit="addQuestion">
                    <b-form-group label="Title">
                        <b-form-textarea id="title"
                                         type="text"
                                         v-model="title"
                                         required>
                        </b-form-textarea>
                    </b-form-group>
                    <b-form-group label="Content">
                        <b-form-textarea id="context"
                                         type="text"
                                         v-model="context"
                                         required
                                         :rows="10">
                        </b-form-textarea>
                        <div>
                            <ring-loader></ring-loader>
                        </div>
                    </b-form-group>
                    <div class="d-flex justify-content-end">
                        <b-button :to="'/question'" variant="secondary">Cancel</b-button>
                        <b-button :disabled="disableSubmitButton"type="submit" variant="primary" class="ml-3">Submit</b-button>
                    </div>
                </b-form>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import api from '../../lib/api';

    export default {
        data: () => ({
            title: '',
            context: '',
            loading: false,
            error: '',
            alertErrorDismissSecs: 3,
            alertErrorDismissCountdown: 0
        }),
        computed: {
            disableSubmitButton: function () {
                return this.title === '' || this.context === '';
            }
        },
        methods: {
            addQuestion() {
                api.defaults.headers.common['Authorization'] = localStorage.getItem('jwt');
                api.put('/question', {
                    user: this.$store.state.userEmail,
                    title: this.title,
                    context: this.context,
                }).then(response => {
                    const createdAt = response.data.createdAt;
                    this.$router.push("/question/"+createdAt);
                }).catch(e => {
                    console.log(e);
                    this.$toasted.error("An error occured while processing the question submission");
                    this.error = e.message;
                });
            },
        },
    };
</script>

<style scoped>

</style>