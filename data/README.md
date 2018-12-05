### Sample Data Generator

We use [generator.js](./generator.js) and [firstQuestion.js](./firstQuestion.js) to generator sample data in .csv files, and then use cassandra-loader to load csv files into cassandra.

#### generator.js
generator.js is used to generate sample codes to qa.question. Since the question in qa.question is seperated by date
(Questions submitted on the same date have the same partition key `date`),
generator.js can be specified how many questions you want to generate for one day (date is senquentially generated from the date generator.js is executed),
and how many days you want for.

Usage:
```
node generator.js <quesetion number a day> <days>
```

Example: I want to generate 10 questions a day for 10000 days (100,000 question in total)
```
node generator.js 10 10000
```

#### firstQuestion.js
firstQuestion.s helps to create the only record into qa.firstQuestion.

Usage:
```
node firstQuestion.js <days>
```
note: The days need to be specified as the same value as the value given to generator.js

#### run.sh
To save time, we can just run run.sh to generate all needed csv files and load it by cassandra-loader.

Usage:
```
NB=<question number a day> DAYS={days} run.sh
```
