<template>
    <div>
        <b-button variant="primary" size="lg" @click="showSettings"><span class="fa fa-cog"></span></b-button>

        <b-modal ref="settings"
                 title='Settings'
                 header-bg-variant='primary'
                 header-text-variant='light'
                 :ok-disabled="!isValid"
                 @ok="handleOk">
            <b-form-group label="Modus">
                <b-form-radio-group
                        v-model="settings.mode"
                        :options="options"
                        name="radio-inline"
                ></b-form-radio-group>
            </b-form-group>
            <label>Number of results</label>
            <b-form-input v-model="settings.num" type="number" :state="isValid"></b-form-input>
            <b-form-invalid-feedback id="validation">
                number must be positive
            </b-form-invalid-feedback>
        </b-modal>
    </div>
</template>

<script>
    export default {
        name: "Settings",
        data() {
            return {
                options: [
                    {text: 'Light', value: 'l'},
                    {text: 'Dark', value: 'd'},
                ]
            }
        },
        computed: {
            isValid() {
                return this.settings.num > 0;
            },
            settings: {
                get() {
                    return this.$store.state.settings;

                }
            }
        },
        methods: {
            showSettings() {
                this.$refs['settings'].show()
            },
            handleOk() {
                localStorage.setItem('settings', JSON.stringify(this.settings));
                this.$store.commit('setSettings', this.settings);
            }

        }
    }
</script>

<style scoped>

</style>