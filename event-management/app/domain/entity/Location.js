'use strict';

const mongoose = require('mongoose');
const uuid = require('node-uuid');

const Schema = mongoose.Schema;

// Defining schema for our Liabilities API
const LocationSchema = Schema({
    uuid: {
        type: String,
        required: true,
        default: uuid.v1
    },
    name: {
        type: String,
        required: true
    },
    latitude: {
        type: Number
    },
    longitude: {
        type: Number
    },
    postal: {
        type: String,
        required: true
    },
    city: {
        type: String,
        required: true
    },
    streetName: {
        type: String,
        required: true
    },
    streetNumber: {
        type: Number,
        required: true
    }
});
//Exporting our model
const LocationModel = mongoose.model('location', LocationSchema);

module.exports = LocationModel;