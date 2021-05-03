import React from 'react';
import {Button, Card, Col, Form} from "react-bootstrap";
import {Search} from "react-bootstrap-icons";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

class GameList extends React.Component {
    render() {
        const {games, onSearch, search, dispatch} = this.props;
        return <>
            <div style={{marginBottom: "30px"}}>
                <Form>
                    <Form.Row>
                        <Col xs={10}>
                            <Form.Control placeholder="Поиск" value={search} onChange={e => dispatch({
                                type: "CHANGE_SEARCH",
                                payload: e.target.value
                            })}/>
                        </Col>
                        <Col>
                            <Button variant="primary" onClick={onSearch}><Search size="20"/></Button>
                        </Col>
                    </Form.Row>
                </Form>
            </div>
            <div>
                {
                    games.map((game, idx) => <Link to={`/games/${game.id}`} key={idx}>
                        <Card style={{display: "inline-block", width: "200px", marginBottom: "10px", marginRight: "10px"}}>
                            <Card.Img variant="top" src={game.cover} />
                            <Card.Body>
                                <Card.Title>{`${game.name} (${game.releaseYear})`}</Card.Title>
                            </Card.Body>
                        </Card>
                    </Link>)
                }
            </div>
        </>
    }

    componentWillUnmount() {
        this.props.dispatch({
            type: 'RESET_SEARCH'
        })
    }
}

function mapStateToProps(state) {
    return {
        search: state.search
    }
}

export default connect(mapStateToProps, null)(GameList);