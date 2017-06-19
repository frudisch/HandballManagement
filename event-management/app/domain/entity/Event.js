'use strict';

const mongoose = require('mongoose');
const uuid = require('node-uuid');

const Schema = mongoose.Schema;

// Defining schema for our Liabilities API
const EventSchema = Schema({
    uuid: {
        type: String,
        required: true,
        default: uuid.v1
    },
    eventName: {
        type: String,
        required: true
    },
    teamUUID: {
        type: String,
        required: true
    },
    repeatable: {
        type: Boolean,
        required: true
    },
    location: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'location'
    }
});
//Exporting our model
const EventModel = mongoose.model('event', EventSchema);

module.exports = EventModel;