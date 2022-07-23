import {Layout, LayoutContent, Select, SelectOption, Button, Drawer, Form, Row, Col, FormItem, Space
    , UploadDragger, Upload, Table, Popconfirm, LayoutFooter, LayoutHeader, Menu, MenuItem
    , InputPassword, LayoutSider, SubMenu, Input} from "ant-design-vue";
const components = [Layout, LayoutContent, Select, SelectOption, Button, Drawer, Form, Row, Col, FormItem, Space
    , UploadDragger, Upload, Table, Popconfirm, LayoutFooter, LayoutHeader, Menu, MenuItem
    , InputPassword, LayoutSider, SubMenu, Input]
export default function setupAtnd(app:any){
    components.forEach((component)=>{
        app.use(component)
    })
}