<template>
    <b-container>
        <div v-if="loaded">
            <div class="mb-3">
                <b-card>
                    <div>
                        <pre><h3>{{question.title}}</h3></pre>
                    </div>
                    <div>
                        <pre>{{question.context}}</pre>
                    </div>
                </b-card>
                <div class="d-flex justify-content-end">
                    {{question.user + " " +formatDate(question.createdAt)}}
                </div>
            </div>
            <div class="mb-3" v-for="answer of question.answers">
                <b-card>
                    <pre>{{answer.context}}</pre>
                </b-card>
                <div class="d-flex justify-content-end">
                    {{answer.user + " " +formatDate(answer.createdAt)}}
                </div>
            </div>
            <b-form @submit="postAnswer">
                <b-form-group>
                    <b-form-textarea id="context"
                                     type="text"
                                     v-model="answer"
                                     required
                                     :rows="5">
                    </b-form-textarea>
                    <div>
                        <ring-loader></ring-loader>
                    </div>
                </b-form-group>
                <div class="d-flex justify-content-end">
                    <b-button :disabled="disableSubmitButton" type="submit" variant="primary" class="ml-3">Submit the reply
                    </b-button>
                </div>
            </b-form>


        </div>
    </b-container>
</template>

<script>
    import api from '../../lib/api';
    import Util from '../../util/Util.js'

    export default {

        data: () => ({
            question: {},
            answer: '',
            loaded:false,
        }),
        mounted: function () {
            this.loadQuestion();
        },
        computed: {
            disableSubmitButton: function () {
                return this.answer === '';
            }
        },
        methods: {
            loadQuestion() {
                api.defaults.headers.common['Authorization'] = localStorage.getItem('jwt');
                api.get('/question/' + this.$route.params.createdAt)
                    .then(response => {
                        this.question = response.data;
                        this.loaded = true;
                    }).catch(e => {
                    console.log(e);
                    this.$toasted.error("An error occurred while looking up questions")
                });
            },
            formatDate(timestamp) {
                return Util.formatDate(timestamp);
            },
            postAnswer() {
                api.defaults.headers.common['Authorization'] = localStorage.getItem('jwt');
                api.put('/answer', {
                    questionCreatedAt: this.$route.params.createdAt,
                    context: this.answer,
                    user: this.$store.state.userEmail,
                })
                    .then(response => {
                        this.loadQuestion();
                        this.answer = '';
                    }).catch(e => {
                    console.log(e);
                    this.$toasted.error("An error occured while posting the reply")
                });
            }
        }
    }
</script>

<style scoped>

</style>