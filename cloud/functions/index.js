const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

exports.watchCreated = functions.firestore
    .document('users/{userId}/watchItems/{itemId}')
    .onCreate((snap,context)=>{
        const value = snap.data();
        console.log(context.params.userId) + "/" + context.params.itemId
        console.log(value)

    });