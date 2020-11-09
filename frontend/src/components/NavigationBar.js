import React from 'react';
import {Navbar, Nav, Dropdown, NavItem, NavLink} from 'react-bootstrap';
import {Link} from "react-router-dom";
import {connect} from "react-redux";

class NavigationBar extends React.Component {
    render() {
        const {user} = this.props;

        return <Navbar expand="md">
            <Navbar.Brand as={Link} to="/">ДолойБэклог!</Navbar.Brand>
            <Navbar.Toggle/>
            <Navbar.Collapse>
                <Nav className="mr-auto">
                    <Nav.Link as={Link} to="/backlog">Бэклог</Nav.Link>
                    <Nav.Link as={Link} to="/games">Игры</Nav.Link>
                </Nav>
                <Nav>
                    {
                        user ? <Dropdown as={NavItem} alignRight={true}>
                            <Dropdown.Toggle as={NavLink}>{user.username}</Dropdown.Toggle>
                            <Dropdown.Menu>
                                <Dropdown.Item href="/logout">Выйти</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                            : <Nav.Link href="/login">Войти</Nav.Link>
                    }

                </Nav>
            </Navbar.Collapse>
        </Navbar>
    }
}

function mapStateToProps(state) {
    return {
        user: state.user
    }
}

export default connect(mapStateToProps, null)(NavigationBar);