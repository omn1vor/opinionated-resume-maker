function getYaml() {
    yaml = {
        yaml: editor.getValue()
    }
    return JSON.stringify(yaml);
}

function getPDF() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/pdf', false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(getYaml());
}

async function getHTML() {
    let response = await fetch('/yaml-to-json', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: getYaml()
    });

    if (!response.ok) {
        console.log(await response.json());
        return;
    }

    let resume = await response.json();
    fillResumeFields(resume);
    showEditor(false);
}

function edit() {
    showEditor(true);
}

function fillResumeFields(resume) {

    let element;

    document.getElementById('name').value = resume.name;
    document.getElementById('role').value = resume.role;

    // contacts
    document.getElementById('email').textContent = resume.email;
    document.getElementById('phone').textContent = resume.phone;
    setLink('linkedIn', resume.linkedIn);
    setLink('github', resume.github);

    // languages
    createList(document.querySelector('#languages > ul'), resume.languages);

    // skills
    createList(document.querySelector('#skills > ul'), resume.languages);
}

function showEditor(turnOn) {
    document.getElementById('resume-html').classList.toggle('hidden');
    document.getElementById('editor-container').classList.toggle('hidden');
    document.getElementById('html-button').classList.toggle('hidden');
    document.getElementById('edit-button').classList.toggle('hidden');
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

