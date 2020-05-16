$(document).ready(function () {

    var current_product_id = null;

    $('#stateSelect').change(function (e) {
        alert($(e.target).val());
    });

    $('#table').on('click', '.table-remove', function () {
        // send remove request to database
        $(this).parents('tr').detach();
    });

    $('#table').on('click', '.table-calculate', function () {
        const $row = $(this).parents('tr');
        current_product_id = $row.index();
        const price = document.getElementById("cena" +  current_product_id).innerText;
        document.getElementById('price-id').setAttribute("placeholder", price + "$");
    });

    $("#calculate-button-id").on('click', function () {
        GetProductPrices();
    });

    // Get Json data
    $.getJSON("/products", function(data) {
        CreateTableFromJSON(data);
    });


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

        const calculateButtonColumn = document.createElement('th');
        calculateButtonColumn.appendChild(document.createTextNode("Oblicz"));
        columns.appendChild(calculateButtonColumn);
        columnsBody.appendChild(columns);

        const removeButtonColumn = document.createElement('th');
        removeButtonColumn.appendChild(document.createTextNode("Usuń"));
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
                cell.setAttribute("id", key + i);
                record.appendChild(cell);
            }


            // ADD CALCULATE MARGIN BUTTON
            const calculateMarginButtonCell = CreateCalculateButton();
            record.appendChild(calculateMarginButtonCell);

            // ADD REMOVE BUTTON
            const removeButtonCell = CreateRemoveButton();
            record.appendChild(removeButtonCell);

            // ADD RECORD TO THE TABLE
            tableBody.appendChild(record);
        }

    }


    function CreateRemoveButton() {
        const cell = document.createElement('td');
        cell.classList.add("table-remove");
        const button = cell.appendChild(document.createElement('button'));
        button.setAttribute("type", "button");
        button.classList.add("btn", "btn-rounded", "btn-danger", "btn-sm");
        button.innerText = "Usuń";
        return cell;
    }


    function CreateCalculateButton() {
        const cell = document.createElement('td');
        cell.classList.add("table-calculate");
        const button = cell.appendChild(document.createElement('button'));
        button.setAttribute("type", "button");
        button.setAttribute("data-toggle", "modal");
        button.setAttribute("data-target", "#calculate-modal");
        button.classList.add("btn", "btn-rounded", "btn-default", "btn-sm");
        button.innerText = "Policz cene";
        return cell;
    }


    function GetProductPrices() {
        var id = current_product_id;
        const product = document.getElementById("nazwa" + id).innerText;
        const price = document.getElementById("cena" + id).innerText;
        const category = document.getElementById("kategoria" + id).innerText;
        const final_price = document.getElementById("final-price-id").value;

        $.getJSON("/calculate/" + product + "/" + price + "/" + category + "/" + final_price, function(data) {
            var select = document.getElementById("state-id");
            const state = select.options[select.selectedIndex].innerText;
            console.log();
            for (const row in data){
                if(data[row]["nameOfState"] === state){
                    document.getElementById("no-tax-price-id").setAttribute("placeholder", data[row]["priceWithoutTaxes"] + "$");
                    document.getElementById("profit-margin-id").setAttribute("placeholder", data[row]["profit"] + "$");
                }
            }
        });

    }

});
