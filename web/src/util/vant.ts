import {Tab, Tabs, Col, Field, Row, Form, CellGroup
    , Button, Toast, Search, PullRefresh, List, Popup
    ,Cell, DropdownItem, Tabbar, TabbarItem, NavBar} from 'vant';
const components = [Tab, Tabs, Col, Field, Row, Form, CellGroup
    , Button, Toast, Search, PullRefresh, List, Popup
    ,Cell, DropdownItem, Tabbar, TabbarItem, NavBar];
export default function setupVant(app:any){
    components.forEach((component)=>{
        app.use(component)
    })
}