import React from 'react';
import {Button} from "react-bootstrap";
import {Plus} from "react-bootstrap-icons";
import GameList from "./GameList";
import axios from "axios";
import {connect} from "react-redux";
import GameModal from "./GameModal";

class Games extends React.Component {
    constructor(props) {
        super(props);
        this.searchGames = this.searchGames.bind(this);
        this.openGameModal = this.openGameModal.bind(this);
        this.closeGameModal = this.closeGameModal.bind(this);
        this.saveGame = this.saveGame.bind(this);
    }

    searchGames() {
        const {search, dispatch} = this.props;
        axios.get(`/api/games/search?search=${search}`)
            .then(response => dispatch({
                type: "ON_LOAD_GAMES",
                payload: response.data
            }))
            .catch(error => console.log(error));
    }

    openGameModal() {
        this.props.dispatch({
            type: "OPEN_NEW_GAME_MODAL"
        });
    }

    closeGameModal() {
        this.props.dispatch({
            type: "CLOSE_NEW_GAME_MODAL"
        });
    }

    saveGame(game) {
        const {games, dispatch} = this.props;
        axios.post('/api/games', game)
            .then((response) => dispatch({
                type: 'ON_LOAD_GAMES',
                payload: [...games, response.data]
            }))
            .catch(error => console.log(error));
    }

    componentDidMount() {
        axios.get("/api/games")
            .then(response => this.props.dispatch({
                type: "ON_LOAD_GAMES",
                payload: response.data
            }))
            .catch(error => console.log(error));
    }

    render() {
        const {games, gameModalVisible, user} = this.props;
        return <>
            <h1>Все игры</h1>
            { user.admin ?
                <div style={{marginBottom: "10px"}}>
                    <Button variant="primary" onClick={this.openGameModal}><Plus size="20"/></Button>
                </div> : null
            }
            <GameList games={games} onSearch={this.searchGames}/>
            {gameModalVisible &&
                <GameModal onClose={this.closeGameModal} onSubmit={this.saveGame}/>
            }
        </>
    }

    componentWillUnmount() {
        this.props.dispatch({
            type: "RESET_GAMES"
        });
    }
}

function mapStateToProps(state) {
    return {
        games: state.games.games,
        gameModalVisible: state.games.gameModalVisible,
        search: state.search,
        user: state.user
    }
}

export default connect(mapStateToProps, null)(Games);