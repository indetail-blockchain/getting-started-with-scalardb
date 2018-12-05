#!/bin/bash
# Usage : insert $NB number of questions every day for $DAYS days.

NB=${NB:-"10"}
DAYS=${DAYS:-"10000"}

node generator.js ${NB} ${DAYS} | ./cassandra-loader -f stdin -host localhost -schema "qa.question(date,id,context,number_of_answers,title,updated_at,user)"
node firstQuestion.js ${DAYS}| ./cassandra-loader -f stdin -host localhost -schema "qa.firstQuestionDate(id,first_question_date)"
