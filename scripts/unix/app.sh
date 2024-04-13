cd ../../app || exit 1

kill $(lsof -t -i:3000) &>/dev/null

npm i
npm start