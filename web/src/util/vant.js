import {Tab, Tabs, Col, Field, Row, Form, CellGroup
, Button, Toast, Search, PullRefresh, List, Popup
,Cell, DropdownItem, Tabbar, TabbarItem, NavBar}  from 'vant';
const vant = {
    install(app){
        app.use(Tab).use(Tabs).use(Col).use(Row).use(Form).use(CellGroup)
            .use(Field).use(Button).use(Toast).use(Search).use(PullRefresh)
            .use(List).use(Popup).use(Cell).use(DropdownItem)
            .use(Tabbar).use(TabbarItem).use(NavBar).use(Toast);
    }
};
export default vant;