<template>
    <div id="ResultList">
        <h6 class="mt-3 ml-2">Results</h6>
        <b-list-group>
            <b-list-group-item button :variant="modevariant" @click="browse(it)" v-for="it in results" v-bind:key="it.idx">
               <p><span class="fa fa-caret-right text-danger" v-if="it.type === 'x'"></span>
                    <span class="fa fa-caret-right text-primary" v-if="it.type === 'b'"></span> {{it.author}}
                   <span class="font-italic">{{ it.title }}</span><br/>
                   &nbsp;&nbsp;<small>{{it.size}}, {{it.ext}}, {{it.date}}</small></p>
            </b-list-group-item>
        </b-list-group>
    </div>

</template>

<script>
    import {baseUrl} from '@/store/index.js'
    
    export default {
        name: "ResultList",
        computed: {
            results: {
                get() {
                    return this.$store.state.results;
                }
            },
            collections: {
                get() {
                    return this.$store.state.collections;
                }
            },
            currcoll : {
                get() {
                    return this.$store.state.currcoll;
                }
            },
            modevariant: {
               get() {
                   if (this.$store.state.settings.mode === 'd')
                       return "dark";
                   else 
                       return "default";
               } 
            }
        },
        methods: {
            browse(it) {
                window.open(baseUrl + "/" + this.collections.find(it => it.value === this.currcoll).text + "/" +
                    it.path, "_blank");
            }
        } 
    }
</script>

<style scoped>

</style>