import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {

    const infoFormSzef = document.getElementById('infoFormSzef');

    infoFormSzef.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySzef();
    fetchAndDisplayCharacters();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayCharacters() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCharacters(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/szefs/' + getParameterByName('szef') + '/pracowniks', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display characters.
 *
 * @param {{pracowniks: {id: int, name:string}[]}} pracowniks
 */
function displayCharacters(pracowniks) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    pracowniks.pracowniks.forEach(pracownik => {
        tableBody.appendChild(createTableRow(pracownik));
    })
    let link = document.getElementById('linkszef');
    link.appendChild(createLinkCell('add', '../add_pracownik/add_pracownik.html?szef='
        + getParameterByName('szef')));

}

/**
 * Creates single table row for entity.
 *
 * @param {{id: int, imie: string}} pracownik
 * @returns {HTMLTableRowElement}
 */
function createTableRow(pracownik) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(pracownik.imie));
    tr.appendChild(createLinkCell('edit', '../character_edit/character_edit.html?szef='
        + getParameterByName('szef') + '&pracownik=' + pracownik.id));
    tr.appendChild(createButtonCell('delete', () => deleteCharacter(pracownik.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} character to be deleted
 */
function deleteCharacter(character) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCharacters();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/szefs/' + getParameterByName('szef')
        + '/pracowniks/' + character, true);
    xhttp.send();
}


/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplaySzef() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySzef(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/szefs/' + getParameterByName('szef'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display user.
 *
 * @param {{id: int, name: string}} szef
 */
function displaySzef(szef) {
    setTextNode('szefname', szef.imie);
    setTextNode('name', szef.imie);
    setTextNode('szefid', szef.id);
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySzef();

        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/szefs/' + getParameterByName('szef'), true);

    const request = {
        'imie': document.getElementById('szname').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.reload();
}