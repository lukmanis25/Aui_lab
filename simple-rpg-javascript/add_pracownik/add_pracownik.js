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

    const infoFormSzef = document.getElementById('addPracownikInfoForm');

    infoFormSzef.addEventListener('submit', event => updateInfoAction(event));

});







function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //fetchAndDisplaySzef();

        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/szefs/' + getParameterByName('szef') + '/pracowniks', true);

    const request = {
        'id' :document.getElementById('pracownikaddid').value,
        'imie': document.getElementById('pracownikaddname').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.reload();
}