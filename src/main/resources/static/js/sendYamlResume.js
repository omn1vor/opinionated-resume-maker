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
    let response = await fetch('/yaml-to-html', {
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

    const resumeHtml = await response.text();
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

