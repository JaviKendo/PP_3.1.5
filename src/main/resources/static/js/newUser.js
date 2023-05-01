$(async function() {
    await newUser();
});

async function newUser() {
    await fetch("http://localhost:8080/api/role")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name;
                el.value = role.id;
                $('#addRoles')[0].appendChild(el);
            })
        })

    const form = document.forms["formNewUser"];

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();
        let addRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) addRoles.push({
                id : form.roles.options[i].value,
                name : form.roles.options[i].name
            })
        }
        fetch("http://localhost:8080/api/admin/addNewUser", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: form.firstName.value,
                lastName: form.lastName.value,
                age: form.age.value,
                email: form.email.value,
                password: form.password.value,
                roles: addRoles
            })
        }).then(() => {
            form.reset();
            allUsers();
            $('#usersTable').click();
        })
    }
}