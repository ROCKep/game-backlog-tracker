import React from 'react';
import axios from "axios";
import {connect} from "react-redux";
import GameList from "./GameList";

class Backlog extends React.Component {

    constructor(props) {
        super(props);
        this.searchGames = this.searchGames.bind(this);
    }

    searchGames() {
        const{search, dispatch} = this.props;
        axios.get(`/api/backlog/search?search=${search}`)
            .then(response => dispatch({
                type: "ON_LOAD_BACKLOG",
                payload: response.data
            }))
            .catch(error => console.log(error));
    }

    componentDidMount() {
        axios.get("/api/backlog")
            .then(response => this.props.dispatch({
                type: "ON_LOAD_BACKLOG",
                payload: response.data
            }))
            .catch(error => console.log(error));
    }

    render() {
        const {games} = this.props;
        return <>
            <h1>Мой бэклог</h1>
            <GameList games={games} onSearch={this.searchGames}/>
        </>
    }

    componentWillUnmount() {
        this.props.dispatch({
            type: "RESET_BACKLOG"
        });
    }
}

function mapStateToProps(state) {
    return {
        games: state.backlog,
        search: state.search
    }
}

export default connect(mapStateToProps, null)(Backlog);