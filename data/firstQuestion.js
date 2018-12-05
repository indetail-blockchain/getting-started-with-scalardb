let days = 0;
if (process.argv.length > 1) {
    days = parseInt(process.argv[2])
}

const msInADay = 86400000;
const startDayTimestamp = (new Date()).getTime() - msInADay * (days -1)

let dateObj = new Date(startDayTimestamp);
let date = dateObj.getFullYear() +
            ("0" + (dateObj.getMonth() + 1)).slice(-2) +
            ("0" + dateObj.getDate()).slice(-2);
        
console.log(`${1},${date}`);