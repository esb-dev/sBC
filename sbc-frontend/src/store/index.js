import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const baseUrl = "http://localhost:7000";

export { baseUrl } 

export default new Vuex.Store({
    strict: true,
    state: {
        collections: [],
        currcoll: 0,
        results: [],
        settings: {mode: 'l', num: 50}
    },
    actions: {
        loadCollections({commit}) {
            axios
                .get(baseUrl + "/collections")
                .then(r => r.data)
                .then(collections => {
                    commit('setCollections', collections)
                })
        },
        searchBooks({commit}, request) {
            axios
                .get(baseUrl + "/ebooks?" + request)
                .then (r => r.data)
                .then ( results => {
                    commit('setResults', results)
                })
        },
        loadSettings({commit}, settings) {
            commit('setSettings', settings)
        }
    },
    mutations: {
        setCollections(state, collections) {
            state.collections = collections;
        },
        setCurrColl(state, currcoll) {
            state.currcoll = currcoll;
        },
        setResults(state, results) {
            state.results = results;
        },
        setSettings(state, settings) {
            state.settings = settings;
        } 
    },
    getters: {
    }
})
