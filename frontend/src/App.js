import React from "react";
import {BrowserRouter, Switch} from 'react-router-dom';
import Backlog from "./components/Backlog";
import NavigationBar from "./components/NavigationBar";
import {Container} from "react-bootstrap";
import {Route} from "react-router";
import Games from "./components/Games";
import Game from "./components/Game";
import axios from "axios";
import {connect} from "react-redux";

class App extends React.Component {

    componentDidMount() {
        axios.get("/api/user").then(response => this.props.dispatch({
            type: "ON_LOAD_USER",
            payload: response.data
        }));
    }

    render() {
      return <>
          <BrowserRouter>
              <NavigationBar/>
              <Container fluid="md">
                  <Route exact path="/backlog" component={Backlog}/>
                  <Route exact path="/games" component={Games}/>
                  <Route exact path="/games/:id" component={Game}/>
              </Container>
          </BrowserRouter>
      </>;
  }
}

export default connect(null, null)(App);
