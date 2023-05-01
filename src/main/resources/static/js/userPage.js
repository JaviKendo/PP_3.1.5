$(async function() {
    await thisUser();
});
async function thisUser() {

    fetch("http://localhost:8080/api/user/getUserInfo")
        .then(res => res.json())
        .then(data => {
            let user = `$(
                <tr>
                    <td>${data.id}</td>
                    <td>${data.firstName}</td>
                    <td>${data.lastName}</td>
                    <td>${data.age}</td>
                    <td>${data.email}</td>
                    <td>${data.roles.map(role => " " + role.name)}</td>
                </tr>
            )`;
            $('#userPanelBody').append(user);
        })
}







// role.substring(1).concat(" ")).toString().replaceAll(",", "");