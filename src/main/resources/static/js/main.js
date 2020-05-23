import axiox from 'axios'
axiox({
    url: 'http://localhost:8080/comment/7'
}).then(res => {
    console.log(res)
})