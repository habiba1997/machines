const express = require('express');
const app = express();
const kafkaConnect = require('./machine-operations');

// init middleware => to parse json fron req.body in post requests
// express.json is direct pass-through of the .json() method from the body-parser module.
app.use(express.json());

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header('Access-Control-Allow-Methods', 'DELETE, PUT');
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    if ('OPTIONS' == req.method) {
        res.sendStatus(200);
    }
    else {
        next();
    }});

// Define Routes
app.use('/redis', require('./routes/read-redis'));

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));
