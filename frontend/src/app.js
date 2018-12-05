import Vue from 'vue';
import App from './component/App.vue';
import router from './route';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Toasted from 'vue-toasted';
import store from './store/store.js'
import './css/style.css'
import InfiniteLoading from 'vue-infinite-loading';

Vue.config.devtools = true;
Vue.config.debug = true;

Vue.use(Toasted, {
    position: 'bottom-center',
    duration: 5000,
});

Vue.use(BootstrapVue);

new Vue({
    el: "#app",
    store,
    router,
    render: h => h(App),
});


