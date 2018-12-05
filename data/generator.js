//Usage : insert [amount] number of questions every day for [days] days.

let amount = 1000;
let days = 100;
if (process.argv.length > 1) {
    amount = parseInt(process.argv[2])
}
if (process.argv.length > 2) {
    days = parseInt(process.argv[3])
}

const msInADay = 86400000;
const startDayTimestamp = (new Date()).getTime() - msInADay * days
for (let j = 1; j <= days; j++) {
    for (let i = 1; i <= amount; i++) {
        let id = startDayTimestamp + msInADay * j + i;
        let dateObj = new Date(id);
        let date = dateObj.getFullYear() +
            ("0" + (dateObj.getMonth() + 1)).slice(-2) +
            ("0" + dateObj.getDate()).slice(-2);
        let title = `Question ${i} of ${dateObj}`;
        let user = "foo@example.com";
        let context = id;
        let updated_at = id;
        let number_of_answers = 0;

        console.log(`${date},${id},${context},${number_of_answers},${title},${updated_at},${user}`);
    }
}
