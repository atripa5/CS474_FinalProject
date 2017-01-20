/**
 * Created by krbalmryde on 12/11/16.
 */

function HandleStream(chart) {
    return (event) => {
        console.log(event);
        let result = JSON.parse(event.data);
        let string = result.sentiment.emotion
                + ":" + result.sentiment.score
                + "\n\t" + result.tweet.author
                + "\n\t\t" + result.tweet.body
            ;
        console.log(string);
        // This is where d3 comes in...
        d3.select("#tweet").selectAll("p")
            .datum(string)
            .html((d) => d)

        chart.datum(DataConverter(result))
    }
}

const colorFunc = d3.scale.linear().range(['red', 'orange', 'grey', 'blue', 'green']).domain([-5,5]);
const sizeFunc = d3.scale.linear().range([-10, 10]).domain([-5,5]);

function DataConverter(result) {
    return {
        category: result.sentiment.emotion,
        time: new Date(),
        type:"circle",
        opacity: 0.8,
        size: Math.abs(sizeFunc(+result.sentiment.score)),
        color: colorFunc(+result.sentiment.score)
    }
}

function SenitmentGraph() {

    let margin = {top: 20, right: 20, bottom: 30, left: 30},
        width = 960 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;

    let chart = realTimeChartMulti()
        .title("Sentimental Twitter")
        .yTitle("Sentiment")
        .xTitle("Time")
        .yDomain(["VERY_POSITIVE", "POSITIVE", "NEUTRAL", "NEGATIVE", "VERY_NEGATIVE"].reverse())
        .border(true)
        .width(width)
        .height(height);
        // .data(data)

    let chartDiv = d3.select("#vizGraph").append('div')
        .attr('id', 'chartDiv')
        .call(chart)

    return chart
}
