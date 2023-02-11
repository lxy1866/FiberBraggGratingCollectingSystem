import axios from "axios";
export default class functionCall {
    handleQueryOnline() {
        return axios.get("/ct/calculateOnLine")
    }
    constructor() {
    }
}