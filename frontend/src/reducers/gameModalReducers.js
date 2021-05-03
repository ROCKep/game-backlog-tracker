const initialState = {
    formData: {
        name: "",
        cover: "",
        releaseDate: "",
        summary: "",
        platforms: [],
        developer: ""
    },
    allDevelopers: [],
    allPlatforms: []
}

const gameModalReducers = (state = initialState, action) => {
    switch (action.type) {
        case 'ON_LOAD_PLATFORMS':
            return {
                ...state,
                allPlatforms: action.payload
            };
        case 'ON_LOAD_DEVELOPERS':
            return {
                ...state,
                allDevelopers: action.payload
            };
        case 'CHANGE_FORM_DATA':
            return {
                ...state,
                formData: action.payload
            };
        case 'RESET_GAME_MODAL':
            return initialState;
        default:
            return state;
    }
};

export default gameModalReducers;