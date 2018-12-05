import axios from 'axios';

export default axios.create({
    baseURL: `${location.protocol}\/\/${CONFIG.api.url}`,
});
