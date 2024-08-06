import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class OsoiteMuokkaus extends Component {

    emptyItem = {
        tunniste: '',
        katuosoite: '',
        postinumero: '',
        postitoimipaikka: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const client = await (await fetch(`http://localhost:8080/osoitteet/${this.props.match.params.id}`)).json();
            this.setState({item: client});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    console.debug("Tunniste on: '" + item.tunniste + "'");

    await fetch('/osoitteet' + (item.tunniste ? '/' + item.tunniste : ''), {
        method: (item.tunniste) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/osoitteet');
}

    render() {
        const {item} = this.state;
        const title = <h2>{item.tunniste ? 'Edit Client' : 'Add Client'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="katuosoite">Katuosoite</Label>
                        <Input type="text" name="katuosoite" id="katuosoite" value={item.katuosoite || ''}
                               onChange={this.handleChange} autoComplete="katuosoite"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="postinumero">Postinumero</Label>
                        <Input type="text" name="postinumero" id="postinumero" value={item.postinumero || ''}
                               onChange={this.handleChange} autoComplete="postinumero"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="postitoimipaikka">Postitoimipaikka</Label>
                        <Input type="text" name="postitoimipaikka" id="postitoimipaikka" value={item.postitoimipaikka || ''}
                               onChange={this.handleChange} autoComplete="postitoimipaikka"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/clients">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(OsoiteMuokkaus);