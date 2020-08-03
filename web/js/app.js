// replace these values with those generated in your TokBox Account
var apiKey = "46463212";
var sessionId = "2_MX40NjQ2MzIxMn5-MTU4MDA5MzQ5MTc2N34xSEs0YzljTkxWNzg0NnZrb2N4N1ZhWkt-fg";
var token = "T1==cGFydG5lcl9pZD00NjQ2MzIxMiZzaWc9ZDMwNWFkZDU3YWY4YmE3YzBjMjQ2ZTBiZDg2OTA3YTA1MmY1NDgxYTpzZXNzaW9uX2lkPTJfTVg0ME5qUTJNekl4TW41LU1UVTRNREE1TXpRNU1UYzJOMzR4U0VzMFl6bGpUa3hXTnpnME5uWnJiMk40TjFaaFdrdC1mZyZjcmVhdGVfdGltZT0xNTgwMDkzNTI5Jm5vbmNlPTAuOTAzODM2NTYwMjU5NjM5MiZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNTgwNjk4MzI3JmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";

// (optional) add server code here
var SERVER_BASE_URL = 'https://prueba1-webrtc.herokuapp.com/';
fetch(SERVER_BASE_URL + '/session').then(function(res) {
    return res.json()
}).then(function(res) {
    apiKey = res.apiKey;
    sessionId = res.sessionId;
    token = res.token;
    initializeSession();
}).catch(handleError);

//*/initializeSession();
// Handling all of our errors here by alerting them
function handleError(error) {
    if (error) {
        alert(error.message);
    }
}

function initializeSession() {
    var session = OT.initSession(apiKey, sessionId);

    // Subscribe to a newly created stream
    session.on('streamCreated', function(event) {
        session.subscribe(event.stream, 'subscriber', {
            insertMode: 'append',
            width: '100%',
            height: '100%'
        }, handleError);
    });
    // Create a publisher
    var publisher = OT.initPublisher('publisher', {
        insertMode: 'append',
        width: '100%',
        height: '100%'
    }, handleError);

    // Connect to the session
    session.connect(token, function(error) {
        // If the connection is successful, publish to the session
        if (error) {
            handleError(error);
        } else {
            session.publish(publisher, handleError);
        }
    });
}

function onfail(error) {
    console.log("permission not granted or system don't have media devices."+error.name);
}
navigator.getUserMedia({audio:true,video:true}, gotStream,onfail);