import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySzefs();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplaySzefs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySzefs(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/szefs', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display users.
 *
 * @param {{szefs: int[]}} szefs
 */
function displaySzefs(szefs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    szefs.szefs.forEach(szef => {
        tableBody.appendChild(createTableRow(szef));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {int} szef
 * @returns {HTMLTableRowElement}
 */
function createTableRow(szef) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(szef.toString()));
    tr.appendChild(createLinkCell('view', '../user_view/user_view.html?szef=' + szef));
    tr.appendChild(createButtonCell('delete', () => deleteSzef(szef)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {int} szef to be deleted
 */
function deleteSzef(szef) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySzefs();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/szefs/' + szef, true);
    xhttp.send();
}
