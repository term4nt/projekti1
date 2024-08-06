import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class OsoiteListaus extends Component {

    constructor(props) {
        super(props);
        this.state = {osoitteet: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/osoitteet')
            .then(response => response.json())
            .then(data => this.setState({osoitteet: data}));
    }

    async remove(tunniste) {
        await fetch(`http://localhost:8080/osoitteet/${tunniste}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let paivitetytOsoitteet = [...this.state.osoitteet].filter(i => i.tunniste !== tunniste);
            this.setState({osoitteet: paivitetytOsoitteet});
        });
    }

    render() {
        const {osoitteet} = this.state;

        const osoiteListaus = osoitteet.map(osoite => {
            return <tr key={osoite.tunniste}>
                <td style={{whiteSpace: 'nowrap'}}>{osoite.katuosoite}</td>
                <td>{osoite.postinumero}</td>
                <td>{osoite.postitoimipaikka}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/osoitteet/" + osoite.tunniste}>Muokkaa</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(osoite.tunniste)}>Poista</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/osoitteet/new">Lisää osoite</Button>
                    </div>
                    <h3>Osoitteet</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="45%">Katuosoite</th>
                            <th width="10%">Postinumero</th>
                            <th width="15%">Postitoimipaikka</th>
                            <th width="30%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {osoiteListaus}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default OsoiteListaus;