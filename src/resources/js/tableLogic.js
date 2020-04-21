function CreateTableFromJSON(data) {

    // CREATE COLUMNS FROM FIRST RECORD KEYS
    const columnsBody = document.getElementById('columnsBody');
    const columns = document.createElement('tr');
    for (const key in data[0]) {
        const columnName = document.createElement('th');
        columnName.classList.add("text-center");
        columnName.appendChild(document.createTextNode(key));
        columns.appendChild(columnName);
    }
    const removeButtonColumn = document.createElement('th');
    removeButtonColumn.appendChild(document.createTextNode("Remove"));
    columns.appendChild(removeButtonColumn);
    columnsBody.appendChild(columns);


    // ADD JSON DATA TO THE TABLE AS ROWS.
    const tableBody = document.getElementById('tableBody');
    for (let i = 0; i < data.length; i++) {

        // CREATE RECORD CONTAINER
        const record = document.createElement('tr');

        // ADD CELLS
        for (const key in data[i]) {
            const cell = document.createElement('td');
            cell.appendChild(document.createTextNode(data[i][key]));
            record.appendChild(cell);
        }

        // ADD REMOVE BUTTON
        const removeButtonCell = CreateRemoveButton();
        record.appendChild(removeButtonCell);

        // ADD RECORD TO THE TABLE
        tableBody.appendChild(record);
    }

}

function CreateRemoveButton() {
    const removeButtonCell = document.createElement('td');
    const span = document.createElement('span');
    span.classList.add("table-remove");
    const button = document.createElement('button');
    button.setAttribute("type", "button");
    button.classList.add("btn", "btn-danger", "btn-rounded", "btn-sm", "my-0");
    button.appendChild(document.createTextNode("Remove"));
    span.appendChild(button);
    removeButtonCell.appendChild(span);
    return removeButtonCell;
}
