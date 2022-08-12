
function getYaml() {
    yaml = {
        yaml: editor.getValue()
    }
    return JSON.stringify(yaml);
}

async function getPDF() {
    const resumeHtml = await getHTML();
    if (!resumeHtml) {
        return;
    }

    let printWindow = window.open('', '', 'height=600,width=800');
    printWindow.document.write(resumeHtml);
    printWindow.document.close();
    printWindow.onload = e => {
        printWindow.document.title = getFileName(printWindow.document);
        printWindow.print();
        printWindow.close();
    }
}

function getFileName(doc) {
    const name = doc.getElementById('name').innerHTML;
    const position = doc.getElementById('position').innerHTML;
    return `Resume - ${name} - ${position}`;
}

async function getHTML() {
    let response = await fetch('/yaml-to-html', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: getYaml()
    });

    if (!response.ok) {
        console.log(await response.json());
        return '';
    }

    const resumeHtml = await response.text();
    return resumeHtml;
}

async function showHTML() {
    const resumeHtml = await getHTML();
    if (!resumeHtml) {
        return;
    }

    const doc = new DOMParser().parseFromString(resumeHtml, 'text/html');
    const resume = doc.getElementById('resume-html');
    const resumeContainer = document.getElementById('resume');
    resumeContainer.textContent = '';
    resumeContainer.appendChild(resume);
    showEditor(false);
}

function edit() {
    showEditor(true);
}

function showEditor(turnOn) {
    document.getElementById('resume').classList.toggle('d-none');
    document.getElementById('editor-container').classList.toggle('d-none');
    document.getElementById('html-button').classList.toggle('d-none');
    document.getElementById('edit-button').classList.toggle('d-none');
}

function createList(parent, list) {
    parent.textContent = '';

    list.forEach(e => {
        let element = document.createElement('li');
        element.textContent = e;
        parent.appendChild(element);
    })
}

function setLink(id, url) {
    element = document.getElementById(id);
    element.href = url;
    element.textContent = element.host + element.pathname;
}

function setLanguage(event) {
    let selected = document.getElementById('locales').value;
    if (!selected) {
        return;
    }

    window.location.replace('?lang=' + selected);
    try {
        localStorage.setItem('lang', selected);
    } catch (ignored) {}
}

document.addEventListener('DOMContentLoaded', () => {
    let element = document.getElementById('locales');
    element.onchange = setLanguage;

    let lang;
    try {
        lang = localStorage.getItem('lang');
    } catch (ignored) {}

    if (!lang) {
        lang = document.documentElement.lang;
    }
    element.value = lang;
});