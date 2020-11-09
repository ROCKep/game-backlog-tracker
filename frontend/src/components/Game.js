import React from 'react';
import {Button, Row, Col, Image} from "react-bootstrap";
import {connect} from "react-redux";
import axios from "axios";
import GameModal from "./GameModal";

class Game extends React.Component {

    constructor(props) {
        super(props);
        this.addGameToBacklog = this.addGameToBacklog.bind(this);
        this.removeGameFromBacklog = this.removeGameFromBacklog.bind(this);
        this.closeGameModal = this.closeGameModal.bind(this);
        this.openGameModal = this.openGameModal.bind(this);
        this.saveGame = this.saveGame.bind(this);
        this.deleteGame = this.deleteGame.bind(this);
    }

    addGameToBacklog() {
        const {history, match: {params: {id}}} = this.props;
        axios.post('/api/backlog', Number.parseInt(id), {
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(() => {
                history.push("/backlog");
            })
            .catch(error => console.log(error));
    }

    removeGameFromBacklog() {
        const {game, history} = this.props;
        axios.delete(`/api/backlog/${game.backlogItemId}`)
            .then(() => {
                history.push("/backlog");
            })
            .catch(error => console.log(error));
    }

    closeGameModal() {
        this.props.dispatch({
            type: 'CLOSE_EDIT_GAME_MODAL'
        });
    }

    openGameModal() {
        this.props.dispatch({
            type: 'OPEN_EDIT_GAME_MODAL'
        });
    }

    saveGame(game) {
        const {match: {params: {id}}, dispatch} = this.props;
        axios.put(`/api/games/${id}`, game)
            .then(() => dispatch({
                type: 'ON_LOAD_GAME',
                payload: game
            }))
            .catch(error => console.log(error));
    }

    deleteGame() {
        const {history, match: {params: {id}}} = this.props;
        axios.delete(`/api/games/${id}`)
            .then(() =>
                history.push('/games'))
            .catch(error => console.log(error));
    }

    componentDidMount() {
        const {match: {params: {id}}} = this.props;
        axios.get(`/api/games/${id}`)
            .then(response => this.props.dispatch({
                type: "ON_LOAD_GAME",
                payload: response.data
            }))
            .catch(error => console.log(error));
    }

    render() {
        const {game, gameModalVisible, user} = this.props;
        return game && <>
            <Row>
                <Col xs={4}>
                    <div style={{marginBottom: "15px"}}>
                        <Image src={game.cover} rounded  style={{maxWidth: "100%"}}/>
                    </div>
                    {game.backlogItemId ? <>
                        <p>Добавлена в бэклог {game.addedToBacklogDate}</p>
                        <div>
                            <Button onClick={this.removeGameFromBacklog} style={{marginBottom: "5px"}}>Удалить из бэклога</Button>
                        </div>
                    </> : <>
                        <div>
                            <Button onClick={this.addGameToBacklog}>Добавить в бэклог</Button>
                        </div>
                    </>}
                    { user.admin ?
                        <>
                        <div>
                            <Button style={{marginBottom: "5px"}} onClick={this.openGameModal}>Редактировать игру</Button>
                        </div>
                        <div>
                            <Button onClick={this.deleteGame}>Удалить игру</Button>
                        </div>
                        </>: null
                    }
                </Col>
                <Col>
                    <h1 style={{marginBottom: "30px"}}>{game.name}</h1>
                    <h3>Дата выхода</h3>
                    <p>{game.releaseDate}</p>
                    <h3>Описание</h3>
                    <p>{game.summary}</p>
                    <h3>Платформы</h3>
                    <p>{game.platforms.join(", ")}</p>
                    <h3>Разработчик</h3>
                    <p>{game.developer}</p>
                </Col>
            </Row>
            {gameModalVisible &&
                <GameModal game={game} onClose={this.closeGameModal} onSubmit={this.saveGame}/>
            }
        </>
    }

    componentWillUnmount() {
        this.props.dispatch({
            type: 'RESET_GAME'
        });
    }
}

function mapStateToProps(state) {
    return {
        game: state.game.game,
        gameModalVisible: state.game.gameModalVisible,
        user: state.user
    }
}

export default connect(mapStateToProps, null)(Game);