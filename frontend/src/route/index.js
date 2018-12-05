import Vue from 'vue';
import VueRouter from 'vue-router';
import Questions from '../component/questions/Questions.vue';
import AddQuestion from '../component/questions/AddQuestion.vue';
import QuestionDetails from '../component/questions/QuestionDetails.vue';
import QuestionsOverview from '../component/questions/QuestionsOverview.vue';
import Login from '../component/Login';

Vue.use(VueRouter);

const routes = [
    {path: '/login', component: Login,},
    {
        path: '/question',
        component: Questions,
        children: [
            {path: '', component: QuestionsOverview, meta: {JwtRequired: true,},},
            {path: 'add', component: AddQuestion, meta: {JwtRequired: true,},},
            {path: ':createdAt', component: QuestionDetails, meta: {JwtRequired: true,},},
        ],
        meta: {
            JwtRequired: true,
        },
    },
];

const router = new VueRouter({routes,});

router.beforeEach((to, from, next) => {
    if (!to.matched.length) {
        next({path: '/question',});
    } else if (to.matched.some(record => record.meta.JwtRequired) && !localStorage.getItem("jwt")) {
        next({path: '/login',});
    } else {
        next();
    }
});

export default router;
