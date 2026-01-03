function getHeader() {
    return document.querySelector('meta[name="_csrf_header"]').content;
}

function getToken() {
    return document.querySelector('meta[name="_csrf"]').content;
}

function getHeaders() {
    return {
        'Content-Type': 'application/json',
        [getHeader()]: getToken()
    }
}
