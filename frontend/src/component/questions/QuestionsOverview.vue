<template>
    <b-container id="main-container">
        <div class="d-flex justify-content-end mt-4 mb-2">
            <b-button :size="lg" variant="primary" v-on:click="addQuestion">
                Submit a question
            </b-button>
        </div>
        <b-card v-for="question of questions" :key="question.createdAt" v-on:click="openDetail(question.createdAt)"
                class="mb-2 ">
            <div class="d-flex card-body p-0">
                <div class="d-flex flex-column align-items-center p-1 rounded-left text-secondary">
                    <div>Reply</div>
                    <div>{{question.numberOfAnswers}}</div>
                </div>
                <div class="d-flex flex-column flex-grow-1 ml-3">
                    <div class="">
                        <h4 class="text-primary">
                            {{question.title}}
                        </h4>
                    </div>
                    <div class="d-flex text-secondary justify-content-end">
                        <div>
                            {{question.user + " "+formatDate(question.createdAt)}}
                        </div>
                    </div>
                </div>
                <hr>
            </div>
        </b-card>
        <infinite-loading @infinite="infiniteHandler">
            <span slot="no-more">
              No more questions to load
            </span>
        </infinite-loading>
    </b-container>
</template>

<script>
    import api from '../../lib/api';
    import Util from '../../util/Util.js'
    import InfiniteLoading from 'vue-infinite-loading';

    export default {
        data: () => ({
            questions: [],
            error: '',
            lastQuerriedDay: '',
            minimal: 10,
            scrollTop: 0,
            innerHeight: 0,
            offsetHeight: 0,
        }),
        mounted() {
        },
        methods: {
            formatDate(timestamp) {
                return Util.formatDate(timestamp)
            },
            addQuestion() {
                this.$router.push('/question/add');
            },
            openDetail(createdAt) {
                this.$router.push("/question/" + createdAt);
            },
            loadNextDataBatch(state) {
                api.defaults.headers.common['Authorization'] = localStorage.getItem('jwt');
                var dayToQuery;
                if (this.lastQuerriedDay === '') {
                    dayToQuery = this.dateToQueryFormat(new Date());
                } else {
                    dayToQuery = this.getPreviousDayTimestamp(this.lastQuerriedDay)
                }
                api.get(`/question?start=${dayToQuery}&minimal=${this.minimal}`)
                    .then(response => {
                        if (response.data.length > 0) {
                            //update lastQuerriedDay
                            this.lastQuerriedDay = response.data[response.data.length - 1].date;
                            this.questions = this.questions.concat(response.data);
                            state.loaded();
                        } else {
                            state.complete();
                        }
                    })
                    .catch(e => {
                        this.error = e;
                        console.log(e);
                        this.$toasted.error("An error occurred while looking up the questions");
                        state.complete();
                    });
            },
            infiniteHandler($state) {
                this.loadNextDataBatch($state);
            },
            getPreviousDayTimestamp(dateString) {
                const currentDay = new Date(dateString.substr(0, 4), parseInt(dateString.substr(4, 2)) - 1, dateString.substr(6, 2));
                const msInADay = 86400000;
                return this.dateToQueryFormat(currentDay.getTime() - msInADay);
            },
            dateToQueryFormat(timestamp) {
                const date = new Date(timestamp);
                const result = date.getFullYear() +
                    ("0" + (date.getMonth() + 1)).slice(-2) +
                    ("0" + date.getDate()).slice(-2);
                return result;
            }
        },
        components: {
            InfiniteLoading,
        },
    }
</script>

<style scoped>

</style>