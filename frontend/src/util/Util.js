export default class Util {
    static formatDate(timestamp) {
        const date = new Date(timestamp);
             return date.getFullYear() + "/" +
            ("0" + (date.getUTCMonth()+1)).slice(-2) + "/" +
            ("0" + date.getDate()).slice(-2) + " " +
            ("0" + date.getHours()).slice(-2) + ":" +
            ("0" + date.getMinutes()).slice(-2);

    }
}