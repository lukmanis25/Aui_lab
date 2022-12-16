import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayCharacter();
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplayCharacter() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            displayPracownik(response)
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/szefs/' + getParameterByName('szef') + '/pracowniks/'
        + getParameterByName('pracownik'), true);
    xhttp.send();
}

function displayPracownik(pracownik) {
    setTextNode('pracownikname', pracownik.imie);
    setTextNode('pname', pracownik.imie);
    setTextNode('pracownikid', pracownik.id);
}
/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCharacter();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/szefs/' + getParameterByName('szef') + '/pracowniks/'
        + getParameterByName('pracownik'), true);

    const request = {
        'imie': document.getElementById('name').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.reload();
}

