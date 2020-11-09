import React from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios from "axios";
import CreatableSelect from "react-select/creatable";
import {connect} from "react-redux";

class GameModal extends React.Component {

    constructor(props) {
        super(props);
        if (props.game) {
            this.props.dispatch({
                type: 'CHANGE_FORM_DATA',
                payload: {
                    name: props.game.name,
                    cover: props.game.cover,
                    summary: props.game.summary,
                    releaseDate: props.game.releaseDate,
                    platforms: props.game.platforms,
                    developer: props.game.developer
                }
            })
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.changeFormData = this.changeFormData.bind(this);
        this.createDeveloper = this.createDeveloper.bind(this);
        this.createPlatform = this.createPlatform.bind(this);
    }

    handleSubmit() {
        const {formData} = this.props;
        this.props.onSubmit(formData);
        this.props.onClose();
    }

    createPlatform(platform) {
        const {allPlatforms, dispatch, formData} = this.props;
        dispatch({
            type: 'ON_LOAD_PLATFORMS',
            payload: [...allPlatforms, {
                value: platform,
                label: platform
            }]
        });
        this.changeFormData("platforms", [...formData.platforms, platform]);
    }

    createDeveloper(developer) {
        const {allDevelopers, dispatch} = this.props;
        dispatch({
            type: 'ON_LOAD_DEVELOPERS',
            payload: [...allDevelopers, {
                value: developer,
                label: developer
            }]
        });
        this.changeFormData("developer", developer);
    }

    componentDidMount() {
        axios.get("/api/platforms")
            .then(response => this.props.dispatch({
                type: 'ON_LOAD_PLATFORMS',
                payload: response.data.map(platform => ({value: platform, label: platform}))
            })).catch(error => console.log(error));
        axios.get("/api/developers")
            .then(response => this.props.dispatch({
                type: 'ON_LOAD_DEVELOPERS',
                payload: response.data.map(developer => ({value: developer, label: developer}))
            })).catch(error => console.log(error));
    }

    render() {
        const {formData, allPlatforms, allDevelopers, game, onClose} = this.props;

        console.log(formData);
        console.log(allPlatforms);
        console.log(allDevelopers);

        return allPlatforms.length > 0 && allDevelopers.length > 0 &&
            <Modal show onHide={onClose}>
                <Modal.Header closeButton>
                    <Modal.Title>{game ? "Редактирование" : "Добавление"} игры</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group>
                            <Form.Label>Название</Form.Label>
                            <Form.Control value={formData.name}
                                          onChange={e => this.changeFormData("name", e.target.value)}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Обложка</Form.Label>
                            <Form.Control value={formData.cover}
                                          onChange={e => this.changeFormData("cover", e.target.value)}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Дата выхода</Form.Label>
                            <Form.Control value={formData.releaseDate}
                                          onChange={e => this.changeFormData("releaseDate", e.target.value)}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Описание</Form.Label>
                            <Form.Control as="textarea" rows={3} value={formData.summary}
                                          onChange={e => this.changeFormData("summary", e.target.value)}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Платформы</Form.Label>
                            <CreatableSelect isMulti options={allPlatforms}
                                    value={allPlatforms.filter(p => formData.platforms.includes(p.value))}
                                    onChange={ps => this.changeFormData("platforms", ps ? ps.map(p => p.value) : [])}
                                    onCreateOption={this.createPlatform}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Разработчик</Form.Label>
                            <CreatableSelect options={allDevelopers}
                                    value={allDevelopers.find(d => d.value === formData.developer)}
                                    onChange={d => this.changeFormData("developer", d.value)}
                                    onCreateOption={this.createDeveloper}/>
                        </Form.Group>
                    </Form>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="primary" onClick={this.handleSubmit}>Сохранить</Button>
                </Modal.Footer>
            </Modal>
    }

    changeFormData(name, value) {
        this.props.dispatch({
            type: 'CHANGE_FORM_DATA',
            payload: {
                ...this.props.formData,
                [name]: value
            }
        })
    }

    componentWillUnmount() {
        this.props.dispatch({
            type: 'RESET_GAME_MODAL'
        })
    }
}

function mapStateToProps(state) {
    return {
        formData: state.gameModal.formData,
        allPlatforms: state.gameModal.allPlatforms,
        allDevelopers: state.gameModal.allDevelopers
    }
}

export default connect(mapStateToProps, null)(GameModal);