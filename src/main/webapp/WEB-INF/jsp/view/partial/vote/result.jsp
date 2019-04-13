<div class="result-content">
    <div class="modal fade result-modal" tabindex="-1" role="dialog" id="result-modal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Vote result</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <canvas id="vote-chart" width="400" height="400"></canvas>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        var ctx = document.getElementById('vote-chart').getContext('2d');
        var colorMapper = [
            {
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
            },
            {
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
            },
            {
                backgroundColor: 'rgba(255, 206, 86, 0.2)',
                borderColor: 'rgba(255, 206, 86, 1)',
            },
            {
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)'
            },
        ];

        var responseLabel = [];
        var backgroundColor = [];
        var borderColor = [];
        var data = [];

        <c:forEach var="response" items="${question.responses}" varStatus="loop">
        responseLabel.push("${response.response}");
        backgroundColor.push(colorMapper[${loop.index}].backgroundColor);
        borderColor.push(colorMapper[${loop.index}].backgroundColor);
        data.push("${response.countVote()}")
        </c:forEach>


        var chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: responseLabel,
                datasets: [{
                    label: '# of Votes',
                    data: data,
                    backgroundColor: backgroundColor,
                    borderColor: borderColor,
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    </script>
</div>